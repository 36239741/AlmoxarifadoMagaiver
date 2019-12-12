import { ItemRetirada } from './../../../shared/model/item-retirada';
import { ItensService } from './../../../shared/services/itens.service';
import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { ITdDataTableColumn, ITdDataTableSelectEvent } from '@covalent/core/data-table';
import { ActivatedRoute, Router } from '@angular/router';
import { BehaviorTitleService } from 'src/app/shared/behavior/behavior-title.service';
import { Item } from 'src/app/shared/model/item';
import { ItemContent } from 'src/app/shared/model/item-content';
import { IPageChangeEvent } from '@covalent/core/paging';
import { BehaviorItemService } from '../../../shared/services/behavior/behavior-item.service';
import { MessageService } from 'src/app/shared/services/message.service';
import { TdDialogService } from '@covalent/core/dialogs';
import { MatDialogConfig } from '@angular/material/dialog';
import { DetalhesRetiradaComponent } from '../detalhes-retirada/detalhes-retirada.component';



const DECIMAL_FORMAT: (v: any) => any = (v: number) => new Intl.NumberFormat('pt-BR',{style: 'currency', currency:'BRL'} ).format(v);

@Component({
  selector: 'app-retirar-item',
  templateUrl: './retirar-item.component.html',
  styleUrls: ['./retirar-item.component.css']
})
export class RetirarItemComponent implements OnInit {

  listItem: Item[] = [];
  pageSize: number = 10;
  item: Item;
  totalPage: any = 0;
  page: number = 0;
  codigo: String = '';
  descricao: String = '';
  localArmazenamento: String = '';
  data: Item[] = [];
  findAll: ItemContent[] = [];
  columns: ITdDataTableColumn[] = [
  {name: 'codigo', label: 'Código'},
  {name: 'descricao' , label: 'Descrição'},
  {name: 'quantidade' , label: 'Quantidade'},
  {name: 'localArmazenamento', label: 'Local de armazenamento'},
  {name: 'valor', label: 'Valor',  numeric: true, format: DECIMAL_FORMAT}
];
selectable: boolean = true;
  constructor(private activedRoute: ActivatedRoute,
              private behaviorTitle: BehaviorTitleService,
              private itenService: ItensService,
              private behaviorItem: BehaviorItemService,
              private router: Router,
              private message: MessageService,
              private _dialogService: TdDialogService,
              private _viewContainerRef: ViewContainerRef) { }

  ngOnInit() {
    this.title();
    this.findAll = this.activedRoute.snapshot.data['findAll'];
    this.data = this.findAll['content'];
    this.zerarQuantidade(this.data);
    this.totalPage = this.findAll['totalElements'];
  }

  title() {
    this.behaviorTitle.setBehaviorTitle(this.activedRoute.snapshot.data['title']);
  }
  zerarQuantidade(data : Item[]) {
    data.forEach(data => {
      data.quantidade = 0;
    });
  }
  filter() {
    this.itenService.findFilter(this.codigo, this.descricao, this.localArmazenamento, 0 , 10).subscribe(data => {
      this.data = [];
      this.data = data.content;
      this.totalPage = data.totalElements;
      this.zerarQuantidade(this.data);
    });
  }
  adicionarItens(){
    if(this.listItem.length > 0){
      this.behaviorItem.itemSet(this.listItem);
      this.router.navigate(['/item-retirada/confirmar-retirada']);
    }
    else{
      this.message.toastError('Nao existe nenhum item na lista');
    }
  }
  rowSelect(event: ITdDataTableSelectEvent) {
    if (this.listItem.indexOf(event.row) < 0 && event.selected != false) {
      this.openPrompt(event);
  } else {
      var index = this.listItem.indexOf(event.row);
      this.listItem.splice(index, 1);
      event.row.quantidade = 0;
  }
  }

  findAllItem() {
    this.itenService.findAllItens(this.page, this.pageSize).subscribe(data => {
      this.data = []
      this.data = data.content;
    });
  }

  pagingEvent(event: IPageChangeEvent) {
    this.page = event.page - 1;
    this.findAllItem();
  }

  filterDescricaoEvent(event: any) {
    this.descricao = event;
    this.filter();
  }
  filterLocalArmazenamentoEvent(event: any) {
    this.localArmazenamento = event;
    this.filter();
  }
  filterCodigoEvent(event: any) {
    this.codigo = event;
    this.filter();
  }
  openPrompt(event: ITdDataTableSelectEvent): void {
    this._dialogService.openPrompt({
      message: 'Quantidade de item a ser retirado',
      disableClose: false, // defaults to false
      viewContainerRef: this._viewContainerRef, //OPTIONAL
      title: 'Retirada', //OPTIONAL, hides if not provided
      value: 'Quantidade de item', //OPTIONAL
      cancelButton: 'Cancel', //OPTIONAL, defaults to 'CANCEL'
      acceptButton: 'Ok', //OPTIONAL, defaults to 'ACCEPT'
      width: '500px', //OPTIONAL, defaults to 400px
    }).afterClosed().subscribe((newValue: string) => {
      if (newValue) {
          let number = Number(newValue);
          console.log(number);
          if(Number.isNaN(number)){
            this.message.toastError('Somente e aceito numeros.');
          }
          else{
            console.log(event.row);
            event.row.quantidade = number
            this.listItem.push(event.row);
          }
      } else {
      }
    });
  }

}
