import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPersoonMySuffix } from 'app/shared/model/persoon-my-suffix.model';

@Component({
  selector: 'jhi-persoon-my-suffix-detail',
  templateUrl: './persoon-my-suffix-detail.component.html'
})
export class PersoonMySuffixDetailComponent implements OnInit {
  persoon: IPersoonMySuffix;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ persoon }) => {
      this.persoon = persoon;
    });
  }

  previousState() {
    window.history.back();
  }
}
