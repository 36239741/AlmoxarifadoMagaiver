import { Item } from './../../model/item';
import { BehaviorSubject } from 'rxjs';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class BehaviorItemService {
  itemRetirada = new BehaviorSubject<Item[]>(null);
  constructor() { }
  itemSet(item: Item[]) {
    this.itemRetirada.next(item);
  }
  itemGet() {
    return this.itemRetirada;
  }
}
