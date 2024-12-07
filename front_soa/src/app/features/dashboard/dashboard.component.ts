import { Component, OnInit } from '@angular/core';
import { CommonModule, NgFor, NgIf } from '@angular/common';
import { CdkDragDrop, DragDropModule, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';
import { ListComponent } from '../list/list.component'; // Ruta del componente List
import { Board } from '../../core/models/board.model';
import { Task } from '../../core/models/task.model';
import { List } from '../../core/models/list.model';
import { HeaderComponent } from "../../shared/components/header/header.component";
import { SharingDataService } from '../../shared/services/sharing-data.service';
import { PopupComponent } from "../../shared/utils/popup/popup.component";
import { ShareWithUsersComponent } from "../users/share-with-users/share-with-users.component";
import { EditTaskComponent } from "../task/edit-task/edit-task.component";
import { TableKanbanService } from '../../core/services/table-kanban.service';
import { AuthService } from '../../core/services/auth.service';
import { TableComponent } from '../../shared/utils/table/table.component';
import { AddTaskComponent } from '../task/add-task/add-task.component';
import { Router, RouterLink } from '@angular/router';
import { GroupService } from '../../core/services/group.service';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [
    CommonModule,
    DragDropModule,
    HeaderComponent,
    PopupComponent,
    ShareWithUsersComponent,
    EditTaskComponent,
    TableComponent,
    AddTaskComponent,
    RouterLink
  ],
  templateUrl: './dashboard.component.html',
  styles: ``
})
export class DashboardComponent implements OnInit {

  userId: number = 0;
  table: { id: number; name: string; columns: any[] } | null = null;
  columnId: any;
  taskId: any;
  groupsLinks: any[] = [];


  editTask: boolean = false;
  createTask: boolean = false;
  addUser: boolean = false;

  constructor(private sharingDataService: SharingDataService,
    private tableKanbanService: TableKanbanService,
    private authService: AuthService,
    private router:Router,
    private groupService: GroupService,

  ) {
  }

  ngOnInit(): void {
    this.userId = this.authService.getUserId();

    this.getTable();

    this.sharingDataService.createTask.subscribe((data: { isCreateTask: boolean; columnId: any }) => {
      this.openModal(false, true, false);

      this.columnId = data.columnId;
    });

    this.sharingDataService.editTask.subscribe((data: { isEditTask: boolean; taskId: any }) => {
      this.openModal(true, false, false);

      this.taskId = data.taskId;
    });

    this.sharingDataService.eventCreateTask.subscribe(() => {
      this.getTable();
      this.closeModal();
    });

    this.sharingDataService.eventEditTask.subscribe(() => {
      this.getTable();
      this.closeModal();
    });
    this.getGroupsLink();
  }

  selectedBoard: Board | null = null;

  selectBoard(board: Board) {
    this.selectedBoard = board;
  }

  createBoard() {
  }

  openModal(editTask: boolean = false, createTask: boolean = false, addUser: boolean = false): void {
    this.editTask = editTask;
    this.createTask = createTask;
    this.addUser = addUser;

    this.sharingDataService.onOpenCloseModal.emit(true);
  }

  addUsers(): void {
    this.openModal(false, false, true);
  }

  closeModal(): void {
    this.sharingDataService.onOpenCloseModal.emit(false);
  }

  getTable() {
    this.tableKanbanService.getTablesByUser(this.userId).subscribe({
      next: (data: any) => {
        this.table = data;
        console.log('Tablero Kanban:', this.table);
      },
      error: (err) => {
        console.error('Error al obtener el tablero:', err);
      }
    });
  }
  getGroupsLink(): void {
    this.groupService.getGroupList(this.authService.getUserId()).subscribe((groups: any[]) => {
      this.groupsLinks = groups;
    });
  }

  logout(): void {
    this.authService.logout();
  }

  
}

