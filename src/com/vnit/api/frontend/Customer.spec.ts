import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { CustomerMasterComponent } from './customer-master.component';
describe('CustomerMasterComponent', () => {
	let component: CustomerMasterComponent;
	let fixture: CustomerFixture<CustomerMasterComponent>;

	beforeEach(async(() => {
		TestBed.configureTestingModule({
			declarations: [ CustomerMasterComponent ]
		})
		.compileComponents();
	}));

	beforeEach(() => {
		fixture = TestBed.createComponent(CustomerMasterComponent);
		component = fixture.componentInstance;
		fixture.detectChanges();
	});

	it('should create', () => {
		expect(component).toBeTruthy();
	});

});

