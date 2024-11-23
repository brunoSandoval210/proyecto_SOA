import { EventEmitter, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SharingDataService {

  constructor() { }

  //Evento para manejar el abrir y cerrar del modal
  private _onOpenCloseModal = new EventEmitter<boolean>();

  private _changeEditTask = new EventEmitter<any>();

  private _createTask = new EventEmitter<any>();

  get onOpenCloseModal(): EventEmitter<boolean> {
    return this._onOpenCloseModal;
  }

  get changeEditTask(): EventEmitter<any> {
    return this._changeEditTask;
  }

  get createTask(): EventEmitter<any> {
    return this._createTask;
  }
}