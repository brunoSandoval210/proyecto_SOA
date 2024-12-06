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
import { BoardComponent } from "../board/board.component";

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
    BoardComponent
  ],
  templateUrl: './dashboard.component.html',
  styles: ``
})
export class DashboardComponent implements OnInit {

  selectedTask: any[] = [];

  userId: number = 0;
  table: { id: number; name: string; columns: any[] } | null = null;
  columnId: any;
  taskId: any;

  editTask: boolean = false;
  createTask: boolean = false;
  addUser: boolean = false;

  constructor(private sharingDataService: SharingDataService,
    private tableKanbanService: TableKanbanService,
    private authService: AuthService
  ) {
  }

  ngOnInit(): void {

    //  // Suscribirse a cambios en la edición de tareas
    //  this.sharingDataService.changeEditTask.subscribe((isOpen: boolean) => {
    //    this.openModal(isOpen, false);
    // });

    // Obtén el ID del usuario
    this.userId = this.authService.getUserId();

    // Carga la tabla inicial
    this.getTable();

    // Suscripción al evento para crear tareas
    this.sharingDataService.createTask.subscribe((data: { isCreateTask: boolean; columnId: any }) => {
      this.openModal(false, true, false);

      // Asigna el columnId recibido desde el evento
      this.columnId = data.columnId;
    });

    // Suscripción al evento para editar tareas
    this.sharingDataService.editTask.subscribe((data: { isEditTask: boolean; taskId: any }) => {
      this.openModal(true, false, false);

      // Asigna el columnId recibido desde el evento
      this.taskId = data.taskId;
    });

    // Evento para actualizar la tabla
    this.sharingDataService.eventCreateTask.subscribe(() => {
      this.getTable();
      this.closeModal();
    });
  }


  // Define tu lista de tableros

  selectedBoard: Board | null = null;

  selectBoard(board: Board) {
    this.selectedBoard = board;
  }

  createBoard() {
    // Lógica para crear un tablero
  }

  openModal(editTask: boolean = false, createTask: boolean = false, addUser: boolean = false): void {
    // Asegura que solo una acción está activa
    this.editTask = editTask;
    this.createTask = createTask;
    this.addUser = addUser;

    // Emitir evento para abrir el modal
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


}
