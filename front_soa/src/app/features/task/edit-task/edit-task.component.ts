import { Component, Input, OnInit, OnChanges, SimpleChanges, DestroyRef } from '@angular/core';
import { TaskService } from '../../../core/services/task.service';
import { AuthService } from '../../../core/services/auth.service';
import { SharingDataService } from '../../../shared/services/sharing-data.service';
import { Task } from '../../../core/models/task.model';
import { FormsModule } from '@angular/forms';
import Swal from 'sweetalert2';
import { CommonModule } from '@angular/common';
import { TableKanbanService } from '../../../core/services/table-kanban.service';

@Component({
  selector: 'app-edit-task',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './edit-task.component.html',
  styleUrls: ['./edit-task.component.scss']
})
export class EditTaskComponent implements OnInit, OnChanges {

  @Input() taskId: any;
  @Input() tableId: any;
  userId: number = 0;

  // Lista de columnas para poblar el combobox
  columns: Array<{ id: number, name: string }> = [];

  task: Task = {
    id: 0,
    title: '',
    descripcion: '',
    limitDate: '',
    nameUser: '',
    priority: 1,
    userId: 0,
    columnId: 0
  };

  constructor(
    private taskService: TaskService,
    private authService: AuthService,
    private sharingDataService: SharingDataService,
    private tableKanbanService: TableKanbanService,
  ) {}

  ngOnInit(): void {
    this.sharingDataService.cleanPopup.subscribe((clean: boolean) => {
      if (clean) {
        this.resetTask();
      }
    });
    this.userId = this.authService.getUserId();
    this.getTask();
    this.getColumns();
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['taskId'] && !changes['taskId'].isFirstChange()) {
      this.getTask();
    }
  }

  getTask(): void {
    this.taskService.getTaskbyID(this.taskId).subscribe(
      (task: Task) => {
        this.task = task;
        console.log(this.task);
      },
      (error) => {
        console.error('Error al obtener la tarea:', error);
      }
    );
  }

  getColumns(){
    this.tableKanbanService.getColumnsByTable(this.tableId).subscribe({
      next: (data: any) => {
        this.columns = data; // Almacenar las columnas
        console.log('Columnas obtenidas:', this.columns);
      },
      error: (err) => {
        console.error('Error al obtener las columnas:', err);
      }
    });
  }

  resetTask(): void {
    this.task = {
      id: 0,
      title: '',
      descripcion: '',
      limitDate: '',
      nameUser: '',
      priority: 1,
      userId: 0
    };
  }

  onSubmit() {
    const taskPayload = {
      column: this.task.columnId,
      title: this.task.title,
      descripcion: this.task.descripcion,
      priority: this.task.priority,
      limitDate: this.task.limitDate,
      user: this.task.userId,
    };

    this.taskService.updateTask(this.taskId, taskPayload).subscribe({
      next: (response) => {
        Swal.fire({
          icon: 'success',
          title: 'Tarea actualizada',
          text: 'La tarea se ha actualizado exitosamente.'
        });
        this.sharingDataService.eventEditTask.emit(true);
        
      },
      error: (err) => {
        Swal.fire({
          icon: 'error',
          title: 'Error',
          text: 'No se pudo actualizar la tarea. Por favor, inténtelo de nuevo.'
        });
        console.error('Error al crear la tarea:', err);
      }
    });
  }
}