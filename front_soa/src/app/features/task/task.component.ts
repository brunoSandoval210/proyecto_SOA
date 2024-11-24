import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Task } from '../../core/models/task.model';
import { SharingDataService } from '../../shared/services/sharing-data.service';

@Component({
  selector: 'app-task',
  standalone: true,
  imports: [],
  templateUrl: './task.component.html',
  styleUrl: './task.component.scss'
})
export class TaskComponent {

  @Input() task!: Task; // Asegúrate de usar @Input para aceptar la propiedad `list`

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
