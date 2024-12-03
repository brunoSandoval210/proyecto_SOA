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
  ];

  selectBoard(boardId: number): Board | undefined {
    return this.boards.find((board) => board.id === boardId);
  }
}
