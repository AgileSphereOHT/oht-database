INSERT INTO public.citizen(
  title, first_name, other_name, last_name, maiden_name, date_of_birth, gender_id, nationality_id, nino, creation_date, last_updated_date)
VALUES ('Mr', 'Justin', null, 'Edwards', null, '20-04-1956', 1, 1, '123456', current_date, current_date);
--insert uk address
INSERT INTO public.address(
  line_one, line_two, line_three, line_four, line_five, line_six, country_id, postcode, address_type_id, correspondence_address, start_date, end_date, creation_date, last_updated_date)
VALUES ('1', 'The Street', 'Preston', 'Lancashire', null, null, 1, 'PR1 1HB', 1, 'true', current_date, null, current_date, current_date);
--map uk addresses to citizen
INSERT INTO public.citizen_address(
  citizen_id, address_id)
VALUES (currval('citizen_citizen_id_seq'), currval('address_address_id_seq'));
--insert foreign address
INSERT INTO public.address(
  line_one, line_two, line_three, line_four, line_five, line_six, country_id, postcode, address_type_id, correspondence_address, start_date, end_date, creation_date, last_updated_date)
VALUES ('98', 'Floral Street', 'Madrid', null, null, null, 3, '378SD', 2, 'false', current_date, null, current_date, current_date);
--map foreign addresses to citizen
INSERT INTO public.citizen_address(
  citizen_id, address_id)
VALUES (currval('citizen_citizen_id_seq'), currval('address_address_id_seq'));
--add contact details
INSERT INTO public.contact_detail(
  contact_detail_type_id, contact
)
VALUES (1, '07700000000');
--add into join table
INSERT INTO public.citizen_contact_detail(
  citizen_id, contact_detail_id
)
VALUES (currval('citizen_citizen_id_seq'), currval('contact_detail_contact_detail_id_seq'));
--add contact details
INSERT INTO public.contact_detail(
  contact_detail_type_id, contact
)
VALUES (3, 'j@bt.com');
--add into join table
INSERT INTO public.citizen_contact_detail(
  citizen_id, contact_detail_id
)
VALUES (currval('citizen_citizen_id_seq'), currval('contact_detail_contact_detail_id_seq'));
--insert registration
INSERT INTO public.registration(
  citizen_id, requested_by, citizen_status_id, benefit_type_id, issue_type, registration_status_id, country_id, entitlement_date, start_date, end_date)
VALUES (currval('citizen_citizen_id_seq'), 'Customer', 1, 1, 'Main', 2, 1, current_date, current_date, null);

--insert pending registration
INSERT INTO public.pending_registration(
  title, first_name, other_name, last_name, maiden_name, date_of_birth, gender_id, nationality_id, nino,
  telephone_number, email_address, current_line_one, current_line_two, current_line_three, current_line_four, current_line_five,
  current_line_six, current_country_id, current_postcode, moving_line_one, moving_line_two, moving_line_three, moving_line_four,
  moving_line_five, moving_line_six, moving_country_id, moving_postcode, moving_date, benefit_type_id, issue_type, registration_status_id,
country_id, entitlement_date, requested_by, has_foreign_pension, occupation_type_id, creation_date, last_updated_date)
VALUES ('Mrs', 'Justine', null, 'Edwards', null, '10-08-1954', 2, 1, '12345679',
        '07700000000', 'je@bt.com', '1', 'The Street', 'Preston', 'Lancashire', null,
        null, 1, 'PR1 1HB', '98', 'Floral Street', 'Madrid', null,
        null, null, 3, '378SD', '10-02-2017', 1, 'Main', 1, 1, current_date, 'Customer', 'no', 1, current_date, current_date);

INSERT INTO public.pending_registration(
  title, first_name, other_name, last_name, maiden_name, date_of_birth, gender_id, nationality_id, nino,
  telephone_number, email_address, current_line_one, current_line_two, current_line_three, current_line_four, current_line_five,
  current_line_six, current_country_id, current_postcode, moving_line_one, moving_line_two, moving_line_three, moving_line_four,
  moving_line_five, moving_line_six, moving_country_id, moving_postcode, moving_date, benefit_type_id, issue_type, registration_status_id,
  country_id, entitlement_date, requested_by, has_foreign_pension, occupation_type_id, creation_date, last_updated_date)
VALUES ('Mrs', 'Heather', null, 'Jones', null, '10-03-1957', 2, 1, '22345679',
        '07800000000', 'hj@bt.com', '15', 'Old Street', 'Preston', 'Lancashire', null,
        null, 1, 'PR2 1HB', '100', 'Rose Street', 'Madrid', null,
        null, null, 3, '478SD', '10-02-2017', 1, 'Main', 1, 1, current_date, 'Customer', 'no', 1, current_date, current_date);

