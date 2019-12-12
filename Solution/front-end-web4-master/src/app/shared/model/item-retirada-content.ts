import { ItemRetirada } from "./item-retirada";
import { Pageable } from "./pageable";
import { Sort } from "./sort";

export class ItemRetiradaContent {
  content?: (ItemRetirada)[] | null;
  pageable: Pageable;
  totalPages: number;
  totalElements: number;
  last: boolean;
  first: boolean;
  sort: Sort;
  numberOfElements: number;
  size: number;
  number: number;
  empty: boolean;
}
