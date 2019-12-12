import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  @Input() placeHolder: String;
  @Output() searchDebounce: EventEmitter<any> = new EventEmitter();
  @Output() clear: EventEmitter<any> = new EventEmitter();

  constructor() { }

  ngOnInit() {
  }
  eventClear(event: any){
    this.clear.emit(event);
  }

  eventDebounce(event: any) {
    this.searchDebounce.emit(event);
  }
}
