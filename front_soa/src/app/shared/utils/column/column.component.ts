import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { TaskComponent } from '../task/task.component';
import { SharingDataService } from '../../services/sharing-data.service';

@Component({
  selector: 'app-column',
  standalone: true,
  imports: [CommonModule, TaskComponent],
  templateUrl: './column.component.html',
  styleUrl: './column.component.scss'
})
export class ColumnComponent {
  @Input() title: string = '';
  @Input() tasks: any[] = [];
  @Input() columnId: any;

  constructor(private sharingDataService: SharingDataService) {

  }

  addTask() {
    this.sharingDataService.createTask.emit({
      create: true,
      columnId: this.columnId
    });
    console.log(this.columnId);
  }
}
