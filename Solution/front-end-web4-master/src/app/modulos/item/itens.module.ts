import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MatToolbarModule} from '@angular/material/toolbar';
import { ScreenComponent } from './screen/screen.component';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {FlexLayoutModule} from '@angular/flex-layout';
import { CardModule } from 'src/app/shared/components/card/card.module';
import { TableModule } from 'src/app/shared/components/table/table.module';
import { SearchModule } from 'src/app/shared/components/search/search.module';
import { FooterModule } from 'src/app/core/footer/footer.module';
import { ItensRoutingModule } from './item.routing';
import { ItemRetiradaComponent } from './item-retirada/item-retirada.component';
import { HeaderModule } from 'src/app/core/header/header.module';
import { MatFormFieldModule } from '@angular/material/form-field';
import { DetalhesRetiradaComponent } from './detalhes-retirada/detalhes-retirada.component';
import { RetirarItemComponent } from './retirar-item/retirar-item.component';
import { ConfirmarRetiradaComponent } from './confirmar-retirada/confirmar-retirada.component';
import { MatInputModule } from '@angular/material/input';
import { ReactiveFormsModule } from '@angular/forms';
import { ConcluirRetiradaComponent } from './concluir-retirada/concluir-retirada.component';
import { CovalentPagingModule } from '@covalent/core/paging';
import { CovalentDialogsModule } from '@covalent/core/dialogs';





@NgModule({
  declarations: [ScreenComponent, ItemRetiradaComponent, DetalhesRetiradaComponent, RetirarItemComponent, ConfirmarRetiradaComponent, ConcluirRetiradaComponent],
  imports: [
    CommonModule,
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    FlexLayoutModule,
    CardModule,
    TableModule,
    SearchModule,
    FooterModule,
    ItensRoutingModule,
    HeaderModule,
    MatFormFieldModule,
    RouterModule,
    MatInputModule,
    ReactiveFormsModule,
    CovalentPagingModule,
    CovalentDialogsModule
  ],
})
export class ItensModule { }
