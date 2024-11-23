import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { TaskComponent } from '../task/task.component';

@Component({
  selector: 'app-column',
  standalone: true,
  imports: [CommonModule,TaskComponent],
  templateUrl: './column.component.html',
  styleUrl: './column.component.scss'
})
export class ColumnComponent {
  @Input() title: string = '';
  @Input() tasks: any[] = [];

  addTask() {
    console.log('Agregar tarea a la columna:', this.title);
  }
}
