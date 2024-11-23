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

  constructor(private sharingDataService: SharingDataService){
  }

  openModal(): void {
    this.sharingDataService.onOpenCloseModal.emit(true);
  }
  
  closeModal(): void {
    this.sharingDataService.onOpenCloseModal.emit(false);
  }

  editTask(): void{
    this.sharingDataService.changeEditTask.emit(true);
  }
}
