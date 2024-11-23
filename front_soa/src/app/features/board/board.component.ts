import { Component } from '@angular/core';
import { List } from '../../core/models/list.model';

@Component({
  selector: 'app-board',
  standalone: true,
  imports: [],
  templateUrl: './board.component.html',
  styleUrl: './board.component.scss'
})
export class BoardComponent {
  lists: List[] = []; // Define tu lista de tableros
  
  selectedList: List | null = null;

  selectList(list: List) {
    this.selectedList = list;
  }

}
