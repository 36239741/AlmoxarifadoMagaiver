import { Item } from './item';
import { Pageable } from './pageable';
import { Sort } from './sort';

export class ItemContent {
  content?: (Item)[] | null;
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
