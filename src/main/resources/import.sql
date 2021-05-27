CREATE TABLE public.hiking (
                               id serial NOT NULL,
                               "name" text NULL,
                               "location" text NULL,
                               length float4 NULL,
                               difficulty text NULL,
                               description text NULL,
                               CONSTRAINT hiking_pk PRIMARY KEY (id)
);


CREATE TABLE public.user_info (
                                  id int4 NOT NULL DEFAULT nextval('user_id_seq'::regclass),
                                  first_name text NULL,
                                  last_name text NULL,
                                  birth_date timestamp(0) NULL,
                                  mobile text NULL,
                                  CONSTRAINT user_pk PRIMARY KEY (id)
);


CREATE TABLE public.user_hiking (
                                    id serial NOT NULL,
                                    user_id int4 NULL,
                                    hiking_id int4 NULL,
                                    CONSTRAINT user_hiking_fk FOREIGN KEY (hiking_id) REFERENCES public.hiking(id),
                                    CONSTRAINT user_hiking_fk_1 FOREIGN KEY (user_id) REFERENCES public.user_info(id)
);



INSERT INTO public.user_info (first_name, last_name, birth_date, mobile) VALUES('user 01', 'test', '2004.06.02', '+758983');
INSERT INTO public.user_info (first_name, last_name, birth_date, mobile) VALUES('user 02', 'test', '2008.09.24', '+553464');
INSERT INTO public.user_info (first_name, last_name, birth_date, mobile) VALUES('user 03', 'test', '2006.10.20', '+352683');
INSERT INTO public.user_info (first_name, last_name, birth_date, mobile) VALUES('user 04', 'test', '1994.06.04', '+872478');
INSERT INTO public.user_info (first_name, last_name, birth_date, mobile) VALUES('user 05', 'test', '1998.02.03', '+124564');
INSERT INTO public.user_info (first_name, last_name, birth_date, mobile) VALUES('user 06', 'test', '1998.04.03', '+741258');
INSERT INTO public.user_info (first_name, last_name, birth_date, mobile) VALUES('user 07', 'test', '1997.08.08', '+159312');
INSERT INTO public.user_info (first_name, last_name, birth_date, mobile) VALUES('user 08', 'test', '2000.03.09', '+369853');
INSERT INTO public.user_info (first_name, last_name, birth_date, mobile) VALUES('user 09', 'test', '1998.07.18', '+245649');
INSERT INTO public.user_info (first_name, last_name, birth_date, mobile) VALUES('user 10', 'test', '2002.06.17', '+124564');
INSERT INTO public.user_info (first_name, last_name, birth_date, mobile) VALUES('user 11', 'test', '2010.08.04', '+654897');
INSERT INTO public.user_info (first_name, last_name, birth_date, mobile) VALUES('user 12', 'test', '2005.06.19', '+652483');


INSERT INTO public.hiking ("name", "location", length, difficulty, description) VALUES('Hiking 01', 'Forest', 16.27, 'MEDIUM', 'descrip for hiking 01');
INSERT INTO public.hiking ("name", "location", length, difficulty, description) VALUES('Hiking 02', 'Desert', 14.33, 'MEDIUM', 'desrip for desert hiking');
INSERT INTO public.hiking ("name", "location", length, difficulty, description) VALUES('Hiking 03', 'Mountain', 8.45, 'MEDIUM', 'descrip');
INSERT INTO public.hiking ("name", "location", length, difficulty, description) VALUES('Hiking 04', 'Ocean', 3.4, 'MEDIUM', 'descripion for ocean');
INSERT INTO public.hiking ("name", "location", length, difficulty, description) VALUES('Hiking 05', 'Sea', 22.36, 'MEDIUM', 'sea tour');
INSERT INTO public.hiking ("name", "location", length, difficulty, description) VALUES('Hiking 06', 'Savannah', 11.24, 'MEDIUM', 'Go to see lions');
INSERT INTO public.hiking ("name", "location", length, difficulty, description) VALUES('Hiking 07', 'City', 6.47, 'MEDIUM', 'Walking in the city');