import { Component, OnInit } from '@angular/core';
import { CommonModule, NgFor, NgIf } from '@angular/common';
import { CdkDragDrop, DragDropModule, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';
import { ListComponent } from '../list/list.component'; // Ruta del componente List
import { Board } from '../../core/models/board.model';
import { Task } from '../../core/models/task.model';
import { List } from '../../core/models/list.model';
import { HeaderComponent } from "../../shared/components/header/header.component";
import { SharingDataService } from '../../shared/services/sharing-data.service';
import { PopupComponent } from "../../shared/utils/popup/popup.component";
import { ShareWithUsersComponent } from "../users/share-with-users/share-with-users.component";
import { TaskComponent } from "../task/task.component";
import { EditTaskComponent } from "../task/edit-task/edit-task.component";

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, DragDropModule, ListComponent, HeaderComponent, PopupComponent, ShareWithUsersComponent, TaskComponent, EditTaskComponent],
  templateUrl: './dashboard.component.html',
  styles: ``
})
export class DashboardComponent implements OnInit{

  isEditMode: boolean = false;
  selectedTask: any [] = [];

  constructor(private sharingDataService: SharingDataService){
  }

  ngOnInit(): void {
    this.sharingDataService.changeEditTask.subscribe((isOpen: boolean) => {
      this.openModal(isOpen);
    });

    console.log(this.isEditMode);
  }

  boards: Board[] = [{
                        id: 1,
                        name: "Tablero SOA",
                        lists: [{
                                id: 1,
                                name: "Por Hacer",
                                tasks: [
                                  {
                                    id: 1, title: "Tarea 1",
                                    assignedTo: undefined
                                  },
                                  {
                                    id: 2, title: "Tarea 2",
                                    assignedTo: undefined
                                  },
                                ]
                              },{
                                id: 2,
                                name: "En curso",
                                tasks: [
                                  {
                                    id: 1, title: "Tarea 1",
                                    assignedTo: undefined
                                  },
                                ]
                              },{
                                id: 3,
                                name: "Hecho",
                                tasks: [
                                  {
                                    id: 1, title: "Tarea 1",
                                    assignedTo: undefined
                                  },
                                  {
                                    id: 2, title: "Tarea 2",
                                    assignedTo: undefined
                                  },
                                ]
                              }]
                      },]; // Define tu lista de tableros
                      
  selectedBoard: Board | null = null;

  selectBoard(board: Board) {
    this.selectedBoard = board;
  }

  createBoard() {
    // Lógica para crear un tablero
  }

  addList() {
    /*if (this.selectedBoard) {
      const newList: List = {
        id: 1,
        name: `Nueva Lista ${this.selectedBoard.lists.length + 1}`,
        tasks: [],
      };
      this.selectedBoard.lists.push(newList);
    }*/
  }

  drop(event: CdkDragDrop<Task[]>) {
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {
      transferArrayItem(
        event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.currentIndex
      );
    }
  }

  openModal(editMode:boolean = false): void {
    this.sharingDataService.onOpenCloseModal.emit(true);
    this.isEditMode = editMode;
  }
  
  closeModal(): void {
    this.sharingDataService.onOpenCloseModal.emit(false);
  }

}
