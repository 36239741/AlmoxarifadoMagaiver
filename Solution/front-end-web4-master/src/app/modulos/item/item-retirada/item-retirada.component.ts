import { ItemRetiradaContent } from './../../../shared/model/item-retirada-content';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ItemRetirada } from './../../../shared/model/item-retirada';
import { ActivatedRoute } from '@angular/router';
import { Component, OnInit, ViewContainerRef} from '@angular/core';
import { ITdDataTableColumn, ITdDataTableRowClickEvent } from '@covalent/core/data-table';
import { BehaviorTitleService } from 'src/app/shared/behavior/behavior-title.service';
import { ItemRetiradaService } from 'src/app/shared/services/item-retirada.service';
import { IPageChangeEvent } from '@covalent/core/paging';
import { MessageService } from 'src/app/shared/services/message.service';
import { MatDialogConfig } from '@angular/material/dialog';
import { TdDialogService } from '@covalent/core/dialogs';
import { DetalhesRetiradaComponent } from '../detalhes-retirada/detalhes-retirada.component';


@Component({
  selector: 'app-item-retirada',
  templateUrl: './item-retirada.component.html',
  styleUrls: ['./item-retirada.component.css']
})
export class ItemRetiradaComponent implements OnInit {
  nome: String = '';
  date: String = '';
  retiradaId: Number = 0;

  formGroup: FormGroup;
  data: any[] = [];
  config: MatDialogConfig;
  dataModal: any;
  pageSize: number = 10;
  totalPage: any = 0;
  page: number = 0;
  tableColumns: ITdDataTableColumn[] = [
  {name: 'id', label: 'Número da retirada'},
  {name: 'quemRetirou' , label: 'Nome'},
  {name: 'data', label: 'Data'},
  {name: 'detalhar', label: 'Detalhar', numeric: true, width:100},
  {name: 'cancelar', label: 'Cancelar', numeric: true, width:100}
];

  constructor(private activedRoute: ActivatedRoute,
              private behaviorTitle: BehaviorTitleService,
              private message: MessageService,
              private itemRetiradaService: ItemRetiradaService,
              private _dialogService: TdDialogService,
              private _viewContainerRef: ViewContainerRef,
              private builder: FormBuilder) { }

  ngOnInit() {
    this.title();
    this.findAllItemRetirada();
    this.form();

  }
  form(){
    this.formGroup = this.builder.group({
      data: ['']
    });
  }
  title() {
    this.behaviorTitle.setBehaviorTitle(this.activedRoute.snapshot.data['title']);
  }
  findAllItemRetirada(){
    this.itemRetiradaService.findAllItemRetirada(this.page , this.pageSize).subscribe(itemRetirada => {
      this.data = itemRetirada.content;
      this.totalPage = itemRetirada.numberOfElements;
      console.log(itemRetirada);
    });
  }

  removeList(itemRetirada: ItemRetirada){
    this.itemRetiradaService.deleteItemRetirada(itemRetirada['id']).subscribe(() => {
      this.message.toastSuccess('Retirada cancelada com sucesso.');
      this.findAllItemRetirada();
    },
    error => {
      this.message.toastError(error.error.message);
    });
  }
  pagingEvent(event: IPageChangeEvent) {
    this.page = event.page - 1;
    this.findAllItemRetirada();
  }
  filter (){
    this.itemRetiradaService.findFiltersItemRetirada(0, 10,this.nome, this.retiradaId, this.date).subscribe(data =>{
      this.data = [];
      this.data = data.content;
    });
  }
  filterRetiradaId(event: any){
    this.retiradaId = event;
    this.filter();
  }
  filterNome(event: any){
    this.nome = event;
    this.filter();
  }
  filterData(){
    this.date = this.formGroup.get('data').value;
    this.filter();
  }
  openComponent(event: any) {
    this.config = {
      width: '90%',
      height: '100%',
      minWidth: '700px',
      minHeight: '600px',
      data: event,
      viewContainerRef: this._viewContainerRef
    };
    this._dialogService.open(DetalhesRetiradaComponent, this.config);
  }

  openConfirm(itemRetirada: ItemRetirada): void {
    this._dialogService.openConfirm({
      message: 'Deseja relamente cancelar essa retirada?',
      disableClose:  false, // defaults to false
      viewContainerRef: this._viewContainerRef, //OPTIONAL
      title: 'Aceitar', //OPTIONAL, hides if not provided
      cancelButton: 'Cancelar', //OPTIONAL, defaults to 'CANCEL'
      acceptButton: 'Aceitar', //OPTIONAL, defaults to 'ACCEPT'
      width: '500px', //OPTIONAL, defaults to 400px
    }).afterClosed().subscribe((accept: boolean) => {
      if (accept) {
        this.removeList(itemRetirada);
      } else {
        // DO SOMETHING ELSE
      }
    });
  }

}
