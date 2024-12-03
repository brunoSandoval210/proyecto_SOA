import { Component, Input } from '@angular/core';
import { SharingDataService } from '../../services/sharing-data.service';

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

  constructor(private sharingDataService: SharingDataService) {
  }

  editTask() {
    this.sharingDataService.editTask.emit({
      edit: true,
      taskId: this.task.id
    });
    console.log(this.task.id);
  }
}
