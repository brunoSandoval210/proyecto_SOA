import { Component, Input } from '@angular/core';
import { List } from '../../core/models/list.model';
import { CommonModule, NgFor, NgIf } from '@angular/common';
import { CdkDragDrop, DragDropModule, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';
import { TaskComponent } from "../task/task.component";

@Component({
  selector: 'app-list',
  standalone: true,
  imports: [CommonModule, TaskComponent DragDropModule],
  templateUrl: './list.component.html',
  styleUrl: './list.component.scss'
})
export class ListComponent {

  @Input() list!: List; // Asegúrate de usar @Input para aceptar la propiedad `list`

  addTask() {
    throw new Error('Method not implemented.');
  }
  
}
