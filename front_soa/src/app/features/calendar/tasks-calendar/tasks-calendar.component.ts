import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-tasks-calendar',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './tasks-calendar.component.html',
  styleUrl: './tasks-calendar.component.scss'
})
export class TasksCalendarComponent {
  @Input() tasks: any[] = [];

}
