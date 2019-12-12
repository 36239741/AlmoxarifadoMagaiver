import { ItemContent } from './../model/item-content';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';


const API_URL = 'http://localhost:4200/v1/itens';
@Injectable({
  providedIn: 'root'
})
export class ItensService {

  constructor(private httpClient: HttpClient) { }

  findAllItens(page: number, size: number): Observable<ItemContent> {
    return this.httpClient.get<ItemContent>(API_URL + '?page=' + page + '&size=' + size);
  }
  findFilter(codigo: String, descricao: String, localArmazenamento: String, page: number, size: number): Observable<ItemContent> {
    return this.httpClient.get<ItemContent>(API_URL + '/item-filter?codigo=' + codigo + '&descricao=' + descricao + '&localArmazenamento=' +
     localArmazenamento + '&page=' + page + '&size=' + size);
  }
}
