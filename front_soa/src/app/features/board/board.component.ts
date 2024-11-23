import { Component } from '@angular/core';
import { List } from '../../core/models/list.model';
import { DashboardComponent } from "../dashboard/dashboard.component";
import { CommonModule, NgFor } from '@angular/common';
import { Board } from '../../core/models/board.model';


@Component({
  selector: 'app-board',
  standalone: true,
  imports: [NgFor, CommonModule],
  templateUrl: './board.component.html',
  styleUrl: './board.component.scss'
})
export class BoardComponent {

  boards: Board[] = [
    {
      id: 1,
      name: 'Mi primer Tablero',
      lists: [
        {
          id: 1,
          name: 'To Do',
          tasks: [
            { id: 1, title: 'Task 1', status: 'Pending', assignedTo: 'User 1' },
            { id: 2, title: 'Task 2', status: 'Pending', assignedTo: 'User 2' },
            { id: 3, title: 'Task 3', status: 'Pending', assignedTo: 'User 3' }
          ]
        },
        {
          id: 2,
          name: 'In Progress',
          tasks: [
            { id: 4, title: 'Task 4', status: 'In Progress', assignedTo: 'User 4' },
            { id: 5, title: 'Task 5', status: 'In Progress', assignedTo: 'User 5' }
          ]
        },
        {
          id: 3,
          name: 'Done',
          tasks: [
            { id: 6, title: 'Task 6', status: 'Completed', assignedTo: 'User 6' }
          ]
        }
      ]
    }
  ];

  selectBoard(boardId: number): Board | undefined {
    return this.boards.find((board) => board.id === boardId);
  }
}
