import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { IPersoonMySuffix } from 'app/shared/model/persoon-my-suffix.model';
import { PersoonMySuffixService } from './persoon-my-suffix.service';

@Component({
  selector: 'jhi-persoon-my-suffix',
  templateUrl: './persoon-my-suffix.component.html'
})
export class PersoonMySuffixComponent implements OnInit, OnDestroy {
  persoons: IPersoonMySuffix[];
  eventSubscriber: Subscription;
  currentSearch: string;

  constructor(
    protected persoonService: PersoonMySuffixService,
    protected eventManager: JhiEventManager,
    protected activatedRoute: ActivatedRoute
  ) {
    this.currentSearch =
      this.activatedRoute.snapshot && this.activatedRoute.snapshot.queryParams['search']
        ? this.activatedRoute.snapshot.queryParams['search']
        : '';
  }

  loadAll() {
    if (this.currentSearch) {
      this.persoonService
        .search({
          query: this.currentSearch
        })
        .subscribe((res: HttpResponse<IPersoonMySuffix[]>) => (this.persoons = res.body));
      return;
    }
    this.persoonService.query().subscribe((res: HttpResponse<IPersoonMySuffix[]>) => {
      this.persoons = res.body;
      this.currentSearch = '';
    });
  }

  search(query) {
    if (!query) {
      return this.clear();
    }
    this.currentSearch = query;
    this.loadAll();
  }

  clear() {
    this.currentSearch = '';
    this.loadAll();
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInPersoons();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IPersoonMySuffix) {
    return item.id;
  }

  registerChangeInPersoons() {
    this.eventSubscriber = this.eventManager.subscribe('persoonListModification', () => this.loadAll());
  }
}
