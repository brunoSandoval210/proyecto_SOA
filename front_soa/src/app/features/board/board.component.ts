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
  lists: List[] = [{
    id: 1, name: "Tablero SOA",
    tasks: []
  }, {
    id: 2, name: "Tablero IDN",
    tasks: []
  }]; // Define tu lista de tableros
  
  selectedList: List | null = null;

  selectList(list: List) {
    this.selectedList = list;
  }

}
