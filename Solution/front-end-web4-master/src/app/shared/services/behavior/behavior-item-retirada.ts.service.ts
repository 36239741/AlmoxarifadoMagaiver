import { ItemRetirada } from './../../model/item-retirada';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';

@Injectable({
  providedIn: 'root'
})
export class BehaviorItemRetirada {

  itemRetirada = new BehaviorSubject<ItemRetirada>(null);

  constructor() { }
  itemRetiradaSet(itemRetirada: ItemRetirada) {
    this.itemRetirada.next(itemRetirada);
  }
  itemRetiradaGet() {
    return this.itemRetirada;
  }
}
