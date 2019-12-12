import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-screen',
  templateUrl: './screen.component.html',
  styleUrls: ['./screen.component.css']
})
export class ScreenComponent implements OnInit {
  houver: boolean = false;
  constructor() { }

  ngOnInit() {
  }
  mouseover(event: any){
    if(event) {
      this.houver = true;
    }
  }
  mouseout(event: any) {
    if(event){
      this.houver = false;
    }
  }
}
