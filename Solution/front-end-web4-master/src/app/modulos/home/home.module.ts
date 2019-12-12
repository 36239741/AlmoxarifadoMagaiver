import { RouterModule } from '@angular/router';
import { FooterModule } from 'src/app/core/footer/footer.module';
import { FlexLayoutModule } from '@angular/flex-layout';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ScreenComponent } from './screen/screen.component';
import { HomeRoutingModule } from './home.routing';
import { BlockFloatComponent } from './block-float/block-float.component';
import { MatButtonModule } from '@angular/material/button';



@NgModule({
  declarations: [ScreenComponent, BlockFloatComponent],
  imports: [
    CommonModule,
    HomeRoutingModule,
    FlexLayoutModule,
    MatButtonModule,
    FooterModule,
    RouterModule
  ]
})
export class HomeModule { }
