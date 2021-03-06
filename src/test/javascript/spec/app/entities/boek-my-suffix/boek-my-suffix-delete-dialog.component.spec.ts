import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { BookApplicationTestModule } from '../../../test.module';
import { BoekMySuffixDeleteDialogComponent } from 'app/entities/boek-my-suffix/boek-my-suffix-delete-dialog.component';
import { BoekMySuffixService } from 'app/entities/boek-my-suffix/boek-my-suffix.service';

describe('Component Tests', () => {
  describe('BoekMySuffix Management Delete Component', () => {
    let comp: BoekMySuffixDeleteDialogComponent;
    let fixture: ComponentFixture<BoekMySuffixDeleteDialogComponent>;
    let service: BoekMySuffixService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BookApplicationTestModule],
        declarations: [BoekMySuffixDeleteDialogComponent]
      })
        .overrideTemplate(BoekMySuffixDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BoekMySuffixDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BoekMySuffixService);
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
