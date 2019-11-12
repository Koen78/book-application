import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BookApplicationTestModule } from '../../../test.module';
import { BoekMySuffixDetailComponent } from 'app/entities/boek-my-suffix/boek-my-suffix-detail.component';
import { BoekMySuffix } from 'app/shared/model/boek-my-suffix.model';

describe('Component Tests', () => {
  describe('BoekMySuffix Management Detail Component', () => {
    let comp: BoekMySuffixDetailComponent;
    let fixture: ComponentFixture<BoekMySuffixDetailComponent>;
    const route = ({ data: of({ boek: new BoekMySuffix(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BookApplicationTestModule],
        declarations: [BoekMySuffixDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(BoekMySuffixDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BoekMySuffixDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.boek).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
