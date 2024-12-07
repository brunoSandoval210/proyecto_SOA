import { Component, OnInit } from '@angular/core';
import { HeaderComponent } from '../../shared/components/header/header.component';
import { SharingDataService } from '../../shared/services/sharing-data.service';
import { AuthService } from '../../core/services/auth.service';
import { BoardComponent } from '../board/board.component';
import { TaskService } from '../../core/services/task.service';
import { ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PopupComponent } from '../../shared/utils/popup/popup.component';
import { TasksCalendarComponent } from './tasks-calendar/tasks-calendar.component';
import { RouterLink } from '@angular/router';
import { GroupService } from '../../core/services/group.service';

@Component({
  selector: 'app-calendar',
  standalone: true,
  imports: [
    HeaderComponent,
    BoardComponent,
    CommonModule,
    PopupComponent,
    TasksCalendarComponent,
    RouterLink
  ],
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.scss']
})
export class CalendarComponent implements OnInit {

  tasks: any[] = [];
  tasksForModal: any[] = []; 
  daysInMonth: number[] = [];
  currentMonth: number = new Date().getMonth();
  isModalOpen:boolean = false;
  currentYear: number = new Date().getFullYear();
  groupsLinks: any[] = [];

  monthNames: string[] = [
    'Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio',
    'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'
  ];
  

  constructor(
    private cdr: ChangeDetectorRef,
    private taskService: TaskService,
    private authService: AuthService,
    private sharingDataService: SharingDataService,
    private groupService: GroupService,
  ) {}
  

  ngOnInit(): void {
    this.loadTasks();
    this.generateDaysInMonth();
    this.getGroupsLink();
  }
  

  loadTasks(): void {
    const userId = this.authService.getUserId();
    this.taskService.getTaskbyIdUser(userId).subscribe((tasks: any[]) => {
      this.tasks = tasks;
    });
  }

  generateDaysInMonth(): void {
    const daysInMonth = new Date(this.currentYear, this.currentMonth + 1, 0).getDate();
    this.daysInMonth = Array.from({ length: daysInMonth }, (_, i) => i + 1);
    this.cdr.detectChanges(); // Forzar actualización
  }
  
  

  getTasksForDay(day: number): any[] {
    const date = new Date(this.currentYear, this.currentMonth, day).toISOString().split('T')[0];
    return this.tasks.filter(task => task.limitDate === date);
  }

  changeMonth(offset: number): void {
    this.currentMonth += offset;
  
    if (this.currentMonth < 0) {
      this.currentMonth = 11;
      this.currentYear--;
    } else if (this.currentMonth > 11) {
      this.currentMonth = 0;
      this.currentYear++;
    }
  
    this.generateDaysInMonth();
  }


  
  openModal(day: number): void {
    this.tasksForModal = this.getTasksForDay(day);
    this.isModalOpen = true;
    this.sharingDataService.onOpenCloseModal.emit(true);
  }
  
  closeModal(): void {
    this.sharingDataService.onOpenCloseModal.emit(false);
    this.isModalOpen = false;
    this.tasksForModal = [];
  }

  getGroupsLink(): void {
    this.groupService.getGroupList(this.authService.getUserId()).subscribe((groups: any[]) => {
      this.groupsLinks = groups;
      console.log(this.groupsLinks);
    });
  }
  

  logout(): void {
    this.authService.logout();
  }
}