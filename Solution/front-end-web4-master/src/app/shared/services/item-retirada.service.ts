import { ItemRetirada } from './../model/item-retirada';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { ItemRetiradaContent } from '../model/item-retirada-content';

const API_URL = 'http://localhost:4200/v1/item_retirada';
@Injectable({
  providedIn: 'root'
})
export class ItemRetiradaService {

  constructor(private http: HttpClient) { }

  findAllItemRetirada(page: number, size: number): Observable<ItemRetiradaContent>{
    return this.http.get<ItemRetiradaContent>(API_URL + '?page=' + page + '&size=' + size);
  }
  saveItemRetirada(itemRetirada: ItemRetirada): Observable<ItemRetirada>{
    return this.http.post<ItemRetirada>(API_URL + '/retirar', itemRetirada);
  }
  deleteItemRetirada(itemRetirada: string): Observable<any>{
    const param = {params: new HttpParams().set('itemRetiradaId', itemRetirada)};
    return this.http.delete(API_URL, param);
  }
  findItemRetiradaId(itemRetiradaId: String): Observable<ItemRetirada> {
    return this.http.get<ItemRetirada>(API_URL + '/' + itemRetiradaId);
  }
  findFiltersItemRetirada(page: number, size: number, nome: String, retiradaId: Number, data: String): Observable<ItemRetiradaContent>{
    return this.http.get<ItemRetiradaContent>(API_URL + '/filters?page=' + page + '&size=' + size + '&quemRetirou=' + nome + '&data=' + data + '&retiradaId=' + retiradaId);
  }

}
