/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { BookApplicationTestModule } from '../../../test.module';
import { PersoonMySuffixComponent } from 'app/entities/persoon-my-suffix/persoon-my-suffix.component';
import { PersoonMySuffixService } from 'app/entities/persoon-my-suffix/persoon-my-suffix.service';
import { PersoonMySuffix } from 'app/shared/model/persoon-my-suffix.model';

describe('Component Tests', () => {
  describe('PersoonMySuffix Management Component', () => {
    let comp: PersoonMySuffixComponent;
    let fixture: ComponentFixture<PersoonMySuffixComponent>;
    let service: PersoonMySuffixService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BookApplicationTestModule],
        declarations: [PersoonMySuffixComponent],
        providers: []
      })
        .overrideTemplate(PersoonMySuffixComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PersoonMySuffixComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PersoonMySuffixService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new PersoonMySuffix(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.persoons[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
