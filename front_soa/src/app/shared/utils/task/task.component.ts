import { Component, Input } from '@angular/core';
import { SharingDataService } from '../../services/sharing-data.service';
import { Task } from '../../../core/models/task.model';
import { TaskService } from '../../../core/services/task.service';

@Component({
  selector: 'app-task',
  standalone: true,
  imports: [],
  templateUrl: './task.component.html',
  styleUrl: './task.component.scss'
})
export class TaskComponent {

  @Input() task: any = {};
  @Input() columnId: any;

  constructor(
    private sharingDataService: SharingDataService,
    private taskService: TaskService) {
  }

  editTask() {
    this.sharingDataService.editTask.emit({
      edit: true,
      taskId: this.task.id
    });

    // Suscribirse al Observable para obtener la tarea
    this.taskService.getTaskbyID(this.task.id).subscribe({
      next: (task) => {
        this.task = task; // Asigna la tarea obtenida al componente
      },
      error: (err) => {
        console.error('Error al obtener la tarea:', err);
      },
    });
  }


}
