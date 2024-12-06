import { Component, Input, OnInit, OnChanges, SimpleChanges } from '@angular/core';
import { TaskService } from '../../../core/services/task.service';
import { AuthService } from '../../../core/services/auth.service';
import { SharingDataService } from '../../../shared/services/sharing-data.service';
import { Task } from '../../../core/models/task.model';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-edit-task',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './edit-task.component.html',
  styleUrls: ['./edit-task.component.scss']
})
export class EditTaskComponent implements OnInit, OnChanges {

  @Input() taskId: any;

  task: Task = {
    id: 0,
    title: '',
    descripcion: '',
    limitDate: '',
    nameUser: '',
    priority: 1,
    userId: 0
  };

  userId: number = 0;

  constructor(
    private taskService: TaskService,
    private authService: AuthService,
    private sharingDataService: SharingDataService
  ) {}

  ngOnInit(): void {
    this.sharingDataService.cleanPopup.subscribe((clean: boolean) => {
      if (clean) {
        this.resetTask();
      }
    });
    this.userId = this.authService.getUserId();
    this.getTask();
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['taskId'] && !changes['taskId'].isFirstChange()) {
      this.getTask();
    }
  }

  getTask(): void {
    this.taskService.getTaskbyID(this.taskId).subscribe(
      (task: Task) => {
        this.task = task;
        console.log(this.task);
      },
      (error) => {
        console.error('Error al obtener la tarea:', error);
      }
    );
  }

  resetTask(): void {
    this.task = {
      id: 0,
      title: '',
      descripcion: '',
      limitDate: '',
      nameUser: '',
      priority: 1,
      userId: 0
    };
  }

  
}