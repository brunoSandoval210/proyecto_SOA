import { ChangeDetectorRef, Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { TaskService } from '../../core/services/task.service';
import { AuthService } from '../../core/services/auth.service';
import { SharingDataService } from '../../shared/services/sharing-data.service';
import { GroupService } from '../../core/services/group.service';
import { HeaderComponent } from '../../shared/components/header/header.component';
import { TableComponent } from '../../shared/utils/table/table.component';
import { CommonModule } from '@angular/common';
import { PopupComponent } from '../../shared/utils/popup/popup.component';
import { EditTaskComponent } from '../task/edit-task/edit-task.component';
import { AddTaskComponent } from '../task/add-task/add-task.component';
import { ShareWithUsersComponent } from '../users/share-with-users/share-with-users.component';

@Component({
  selector: 'app-group',
  standalone: true,
  imports: [RouterLink,
    HeaderComponent,
    TableComponent,
    CommonModule,
    PopupComponent,
    AddTaskComponent,
    EditTaskComponent,
    ShareWithUsersComponent
  ],
  templateUrl: './group.component.html',
  styleUrl: './group.component.scss'
})
export class GroupComponent implements OnInit{
  groupId: number | null = null;
  groupsLinks: any[] = [];
  table: { id: number; name: string; columns: any[] } | null = null;
  editTask: boolean = false;
  columnId: any;
  taskId: any;
  addUser: boolean = false;

  createTask: boolean = false;


  constructor(
    private route: ActivatedRoute,
    private authService: AuthService,
    private groupService: GroupService,
    private sharingDataService: SharingDataService,

  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe((params) => {
      const id = params.get('groupId');
      if (id) {
        this.groupId = +id;
  
        this.getTableGroup();
        this.getGroupsLink();
      }
    });
    this.sharingDataService.createTask.subscribe((data: { isCreateTask: boolean; columnId: any }) => {
      this.openModal(false, true, false);

      this.columnId = data.columnId;
    });

    this.sharingDataService.editTask.subscribe((data: { isEditTask: boolean; taskId: any }) => {
      this.openModal(true, false, false);

      this.taskId = data.taskId;
    });

    this.sharingDataService.eventCreateTask.subscribe(() => {
      this.getTableGroup();
      this.closeModal();
    });

    this.sharingDataService.eventEditTask.subscribe(() => {
      this.getTableGroup();
      this.closeModal();
      
    });
  }
  openModal(editTask: boolean = false, createTask: boolean = false, addUser: boolean = false): void {
    this.editTask = editTask;
    this.createTask = createTask;
    this.addUser = addUser;

    this.sharingDataService.onOpenCloseModal.emit(true);
  }
  
  closeModal(): void {
    this.sharingDataService.onOpenCloseModal.emit(false);
  }

  getTableGroup(): void {
    if (!this.groupId) {
      console.error('No se encontró un ID válido para el grupo.');
      return;
    }
  
    this.groupService.getGroupById(this.groupId).subscribe(
      (table: any) => {
        this.table = table.tableKanbanResponse;
        console.log('Datos de la tabla:', this.table);
      },
      (error) => {
        console.error('Error al cargar la tabla del grupo:', error);
      }
    );
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
