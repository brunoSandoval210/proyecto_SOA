import { Component, Input, OnInit } from '@angular/core';
import { TaskComponent } from '../../../shared/utils/task/task.component';
import { TaskService } from '../../../core/services/task.service';
import { AuthService } from '../../../core/services/auth.service';
import { SharingDataService } from '../../../shared/services/sharing-data.service';
import Swal from 'sweetalert2';
import { Task } from '../../../core/models/task.model';

@Component({
  selector: 'app-edit-task',
  standalone: true,
  imports: [],
  templateUrl: './edit-task.component.html',
  styleUrl: './edit-task.component.scss'
})
export class EditTaskComponent implements OnInit {

  //setear los datos en el componente
  @Input() taskId: any;

  task: Task | undefined;

  userId: number = 0; // ID del usuario que crea la tarea

  constructor(
    private taskService: TaskService,
    private authService: AuthService,
    private sharingDataService: SharingDataService) { }

    

  ngOnInit(): void {
    this.userId = this.authService.getUserId();
    this.getTask();
  }

  getTask():void {
    // Suscribirse al Observable para obtener la tarea
    this.taskService.getTaskbyID(this.taskId).subscribe({
      next: (task) => {
        this.task = task; // Asigna la tarea obtenida al componente
        console.log(task);
      },
      error: (err) => {
        console.error('Error al obtener la tarea:', err);
      },
    });
  }

  //Enviar los datos para su actualizacion
  // onSubmit() {
  //   const taskPayload = {
  //     column: this.columnId,
  //     title: this.title,
  //     descripcion: this.descripcion,
  //     priority: this.priority,
  //     limitDate: this.limitDate,
  //     user: this.userId
  //   };

  //   this.taskService.updateTask(this.columnId, taskPayload).subscribe({
  //     next: (response) => {
  //       Swal.fire({
  //         icon: 'success',
  //         title: 'Tarea creada',
  //         text: 'La tarea se ha creado exitosamente.'
  //       });
  //       this.sharingDataService.eventEditTask.emit(true);

  //     },
  //     error: (err) => {
  //       Swal.fire({
  //         icon: 'error',
  //         title: 'Error',
  //         text: 'No se pudo crear la tarea. Por favor, inténtelo de nuevo.'
  //       });
  //       console.error('Error al crear la tarea:', err);
  //     }
  //   });
  // }
}

