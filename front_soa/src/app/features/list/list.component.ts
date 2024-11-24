import { Component, Input } from '@angular/core';
import { List } from '../../core/models/list.model';
import { CommonModule, NgFor, NgIf } from '@angular/common';
import { CdkDragDrop, DragDropModule, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';
import { TaskComponent } from "../task/task.component";
import { Task } from '../../core/models/task.model';

@Component({
  selector: 'app-list',
  standalone: true,
  imports: [CommonModule, TaskComponent, DragDropModule],
  templateUrl: './list.component.html',
  styleUrl: './list.component.scss'
})
export class ListComponent {

  @Input() list!: List;

  addTask() {
    const newTask: Task = {
      id: 1,
      title: `Nueva Tarea ${this.list.tasks.length + 1}`,
      assignedTo: undefined
    };
    this.list.tasks.push(newTask);
  }

  drop(event: CdkDragDrop<Task[]>) {
    moveItemInArray(this.list.tasks, event.previousIndex, event.currentIndex);
  }
}
