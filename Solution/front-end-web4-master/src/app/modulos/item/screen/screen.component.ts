import { ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { BehaviorTitleService } from '../../../shared/behavior/behavior-title.service';

@Component({
  selector: 'app-screen',
  templateUrl: './screen.component.html',
  styleUrls: ['./screen.component.css']
})
export class ScreenComponent implements OnInit {
  title: String = 'Almoxarifado Magaiver';
  titleCard: String = ''
  constructor(private behaviorTitle: BehaviorTitleService,
              private activedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.titleBehavior();

  }
  titleBehavior(){
    this.behaviorTitle.setBehaviorTitle(this.activedRoute.snapshot.data['title']);
    this.behaviorTitle.getBehaviorTitle().subscribe(data => {
      this.titleCard = data;
    });
  }
}
