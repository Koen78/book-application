import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBoekMySuffix } from 'app/shared/model/boek-my-suffix.model';

@Component({
  selector: 'jhi-boek-my-suffix-detail',
  templateUrl: './boek-my-suffix-detail.component.html'
})
export class BoekMySuffixDetailComponent implements OnInit {
  boek: IBoekMySuffix;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ boek }) => {
      this.boek = boek;
    });
  }

  previousState() {
    window.history.back();
  }
}
