import { Component, Input, OnInit } from '@angular/core';
import { SharingDataService } from '../../services/sharing-data.service';
import { Task } from '../../../core/models/task.model';
import { TaskService } from '../../../core/services/task.service';
import { CommonModule } from '@angular/common';
import { ColumnService } from '../../../core/services/column.service';
import { Column } from '../../../core/models/column.model';

@Component({
  selector: 'app-task',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './task.component.html',
  styleUrl: './task.component.scss'
})
export class TaskComponent implements OnInit {

  @Input() task: any = {};
  columnId: any;
  column: Column | undefined;
  title: any;


  constructor(
    private sharingDataService: SharingDataService,
    private taskService: TaskService,
    private columnService: ColumnService) {
  }

  ngOnInit(): void {
    this.getTask();
    this.getColumn();
  }

  editTask() {
    this.sharingDataService.editTask.emit({
      edit: true,
      taskId: this.task.id
    });
    this.getTask();
  }

  getTask() {
    // Suscribirse al Observable para obtener la tarea
    this.taskService.getTaskbyID(this.task.id).subscribe({
      next: (task) => {
        this.task = task;
        this.columnId = task.columnId; // Asigna la tarea obtenida al componente
      },
      error: (err) => {
        console.error('Error al obtener la tarea:', err);
      },
    });
  }

  getColumn(){
    this.columnService.getColumnbyID(this.columnId).subscribe({
      next: (column) => {
        this.title = column.name; // Asigna la tarea obtenida al componente
      },
      error: (err) => {
        console.error('Error al obtener la tarea:', err);
      },
    });
  }
}
