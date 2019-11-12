import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { BookApplicationTestModule } from '../../../test.module';
import { PersoonMySuffixUpdateComponent } from 'app/entities/persoon-my-suffix/persoon-my-suffix-update.component';
import { PersoonMySuffixService } from 'app/entities/persoon-my-suffix/persoon-my-suffix.service';
import { PersoonMySuffix } from 'app/shared/model/persoon-my-suffix.model';

describe('Component Tests', () => {
  describe('PersoonMySuffix Management Update Component', () => {
    let comp: PersoonMySuffixUpdateComponent;
    let fixture: ComponentFixture<PersoonMySuffixUpdateComponent>;
    let service: PersoonMySuffixService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BookApplicationTestModule],
        declarations: [PersoonMySuffixUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(PersoonMySuffixUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PersoonMySuffixUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PersoonMySuffixService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new PersoonMySuffix(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new PersoonMySuffix();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
