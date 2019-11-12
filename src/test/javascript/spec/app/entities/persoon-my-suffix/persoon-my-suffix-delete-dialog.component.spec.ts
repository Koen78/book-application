/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { BookApplicationTestModule } from '../../../test.module';
import { PersoonMySuffixDeleteDialogComponent } from 'app/entities/persoon-my-suffix/persoon-my-suffix-delete-dialog.component';
import { PersoonMySuffixService } from 'app/entities/persoon-my-suffix/persoon-my-suffix.service';

describe('Component Tests', () => {
  describe('PersoonMySuffix Management Delete Component', () => {
    let comp: PersoonMySuffixDeleteDialogComponent;
    let fixture: ComponentFixture<PersoonMySuffixDeleteDialogComponent>;
    let service: PersoonMySuffixService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BookApplicationTestModule],
        declarations: [PersoonMySuffixDeleteDialogComponent]
      })
        .overrideTemplate(PersoonMySuffixDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PersoonMySuffixDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PersoonMySuffixService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
