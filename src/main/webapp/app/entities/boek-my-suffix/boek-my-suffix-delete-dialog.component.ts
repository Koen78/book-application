import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBoekMySuffix } from 'app/shared/model/boek-my-suffix.model';
import { BoekMySuffixService } from './boek-my-suffix.service';

@Component({
  selector: 'jhi-boek-my-suffix-delete-dialog',
  templateUrl: './boek-my-suffix-delete-dialog.component.html'
})
export class BoekMySuffixDeleteDialogComponent {
  boek: IBoekMySuffix;

  constructor(protected boekService: BoekMySuffixService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.boekService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'boekListModification',
        content: 'Deleted an boek'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-boek-my-suffix-delete-popup',
  template: ''
})
export class BoekMySuffixDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ boek }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(BoekMySuffixDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.boek = boek;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/boek-my-suffix', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/boek-my-suffix', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          }
        );
      }, 0);
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }
}
