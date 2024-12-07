import { Component, OnInit } from '@angular/core';
import { HeaderComponent } from '../../shared/components/header/header.component';
import { SharingDataService } from '../../shared/services/sharing-data.service';
import { AuthService } from '../../core/services/auth.service';
import { Router } from '@angular/router';
import { BoardComponent } from '../board/board.component';
import { TaskService } from '../../core/services/task.service';
import { ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-calendar',
  standalone: true,
  imports: [
    HeaderComponent,
    BoardComponent,
    CommonModule
  ],
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.scss']
})
export class CalendarComponent implements OnInit {

  tasks: any[] = [];
  daysInMonth: number[] = [];
  currentMonth: number = new Date().getMonth();
  currentYear: number = new Date().getFullYear();

  monthNames: string[] = [
    'Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio',
    'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'
  ];
  

  constructor(
    private cdr: ChangeDetectorRef,
    private sharingDataService: SharingDataService,
    private taskService: TaskService,
    private authService: AuthService,
    private router: Router
  ) {}
  

  ngOnInit(): void {
    console.log('Mes actual:', this.currentMonth);
    console.log('Año actual:', this.currentYear);
    this.loadTasks();
    this.generateDaysInMonth();
  }
  

  loadTasks(): void {
    const userId = this.authService.getUserId();
    this.taskService.getTaskbyIdUser(userId).subscribe((tasks: any[]) => {
      this.tasks = tasks;
      console.log('Tareas cargadas:', this.tasks); // Verifica que las tareas se carguen correctamente
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
  
    // Cambiar de año si es necesario
    if (this.currentMonth < 0) {
      this.currentMonth = 11;
      this.currentYear--;
    } else if (this.currentMonth > 11) {
      this.currentMonth = 0;
      this.currentYear++;
    }
  
    // Regenerar los días del mes
    this.generateDaysInMonth();
  }
  

  logout(): void {
    this.authService.logout();
  }
}