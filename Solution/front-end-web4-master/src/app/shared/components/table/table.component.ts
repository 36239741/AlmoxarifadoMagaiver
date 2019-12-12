import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { ITdDataTableColumn, ITdDataTableSelectEvent, ITdDataTableRowClickEvent } from '@covalent/core/data-table';
import { FloatLabelType } from '@angular/material/core';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit {
  floatLabelType: FloatLabelType = 'never';
  @Input() clicavel: false;
  @Input() columns: ITdDataTableColumn;
  @Input() data: any;
  @Input() selectable: boolean = false;
  @Input() firstLast: any;
  @Input() pageSize: any;
  @Input() totalPage: any;
  @Output() changeEvent: EventEmitter<any> = new EventEmitter();
  @Output() removeList: EventEmitter<any> = new EventEmitter();
  @Output() detalharList: EventEmitter<any> = new EventEmitter();
  @Output() rowClick: EventEmitter<ITdDataTableRowClickEvent> = new EventEmitter();
  @Output() rowSelectEvent: EventEmitter<ITdDataTableSelectEvent> = new EventEmitter();
  constructor() { }

  ngOnInit() {
  }
  change(event: any){
    this.changeEvent.emit(event);
  }
  rowSelect(event:any) {
    this.rowSelectEvent.emit(event);
  }
  removeListEvent(event: any){
    this.removeList.emit(event);
  }
  rowClickEvent(event: ITdDataTableRowClickEvent){
    this.rowClick.emit(event);
  }
  detalharListEvent(event: any){
    this.detalharList.emit(event);

  }
}
