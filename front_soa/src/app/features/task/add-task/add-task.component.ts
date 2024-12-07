import { Component, Input, OnInit } from '@angular/core';
import { TaskService } from '../../../core/services/task.service';
import Swal from 'sweetalert2';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../../core/services/auth.service';
import { SharingDataService } from '../../../shared/services/sharing-data.service';

@Component({
  selector: 'app-add-task',
  standalone: true,
  imports: [CommonModule,FormsModule],
  templateUrl: './add-task.component.html',
  styleUrl: './add-task.component.scss'
})
export class AddTaskComponent implements OnInit{
  @Input() columnId:any;
  title: string = '';
  descripcion: string = '';
  priority: number = 1; // Nivel de prioridad
  limitDate: string = '2024-12-24'; // Fecha límite
  userId: number = 0; // ID del usuario que crea la tarea

  constructor(
    private taskService: TaskService,
    private authService:AuthService,
    private sharingDataService:SharingDataService) {}

  ngOnInit(): void {
      this.userId = this.authService.getUserId();
  }

  onSubmit() {
    const taskPayload = {
      column: this.columnId,
      title: this.title,
      descripcion: this.descripcion,
      priority: this.priority,
      limitDate: this.limitDate,
      user: this.userId
    };

    this.taskService.createTask(taskPayload).subscribe({
      next: (response) => {
        Swal.fire({
          icon: 'success',
          title: 'Tarea creada',
          text: 'La tarea se ha creado exitosamente.'
        });
        this.sharingDataService.eventCreateTask.emit(true);
        
      },
      error: (err) => {
        Swal.fire({
          icon: 'error',
          title: 'Error',
          text: 'No se pudo crear la tarea. Por favor, inténtelo de nuevo.'
        });
        console.error('Error al crear la tarea:', err);
      }
    });
  }
}
