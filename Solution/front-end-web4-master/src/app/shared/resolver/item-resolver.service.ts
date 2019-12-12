import { Injectable } from '@angular/core';
import { Resolve } from '@angular/router';
import { ItensService } from '../../shared/services/itens.service';
import { ItemContent } from '../model/item-content';

@Injectable({
  providedIn: 'root'
})
export class ItemResolverService implements Resolve<ItemContent>{


  constructor(private itemService: ItensService) { }
  resolve(route: import("@angular/router").ActivatedRouteSnapshot, state: import("@angular/router").RouterStateSnapshot): ItemContent | import("rxjs").Observable<ItemContent> | Promise<ItemContent> {
    return this.itemService.findAllItens(0 , 10);
  }
}
