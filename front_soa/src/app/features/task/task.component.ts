import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Task } from '../../core/models/task.model';

@Component({
  selector: 'app-task',
  standalone: true,
  imports: [],
  templateUrl: './task.component.html',
  styleUrl: './task.component.scss'
})
export class TaskComponent {

  @Input() task!: Task; // Asegúrate de usar @Input para aceptar la propiedad `list`

}
