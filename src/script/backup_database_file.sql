PGDMP                          {           Java_Sprint-2    15.1    15.1 +    5           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            6           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            7           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            8           1262    17377    Java_Sprint-2    DATABASE     q   CREATE DATABASE "Java_Sprint-2" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'C';
    DROP DATABASE "Java_Sprint-2";
                postgres    false            �            1259    17447    appointments    TABLE     ~   CREATE TABLE public.appointments (
    doctor_id integer NOT NULL,
    patient_id integer NOT NULL,
    date date NOT NULL
);
     DROP TABLE public.appointments;
       public         heap    postgres    false            �            1259    17392    doctor_patient    TABLE     h   CREATE TABLE public.doctor_patient (
    doctor_id integer NOT NULL,
    patient_id integer NOT NULL
);
 "   DROP TABLE public.doctor_patient;
       public         heap    postgres    false            �            1259    17408    health_data    TABLE     �   CREATE TABLE public.health_data (
    id integer NOT NULL,
    user_id integer NOT NULL,
    weight numeric(5,2) NOT NULL,
    height numeric(5,2) NOT NULL,
    steps integer NOT NULL,
    heart_rate integer NOT NULL,
    date date NOT NULL
);
    DROP TABLE public.health_data;
       public         heap    postgres    false            �            1259    17407    health_data_id_seq    SEQUENCE     �   CREATE SEQUENCE public.health_data_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.health_data_id_seq;
       public          postgres    false    218            9           0    0    health_data_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.health_data_id_seq OWNED BY public.health_data.id;
          public          postgres    false    217            �            1259    17420    medicine_reminders    TABLE     (  CREATE TABLE public.medicine_reminders (
    id integer NOT NULL,
    user_id integer NOT NULL,
    medicine_name character varying(100) NOT NULL,
    dosage character varying(50) NOT NULL,
    schedule character varying(100) NOT NULL,
    start_date date NOT NULL,
    end_date date NOT NULL
);
 &   DROP TABLE public.medicine_reminders;
       public         heap    postgres    false            �            1259    17419    medicine_reminders_id_seq    SEQUENCE     �   CREATE SEQUENCE public.medicine_reminders_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 0   DROP SEQUENCE public.medicine_reminders_id_seq;
       public          postgres    false    220            :           0    0    medicine_reminders_id_seq    SEQUENCE OWNED BY     W   ALTER SEQUENCE public.medicine_reminders_id_seq OWNED BY public.medicine_reminders.id;
          public          postgres    false    219            �            1259    17432    recommendations    TABLE     �   CREATE TABLE public.recommendations (
    id integer NOT NULL,
    user_id integer NOT NULL,
    recommendation_text text NOT NULL,
    date date NOT NULL
);
 #   DROP TABLE public.recommendations;
       public         heap    postgres    false            �            1259    17431    recommendations_id_seq    SEQUENCE     �   CREATE SEQUENCE public.recommendations_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.recommendations_id_seq;
       public          postgres    false    222            ;           0    0    recommendations_id_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.recommendations_id_seq OWNED BY public.recommendations.id;
          public          postgres    false    221            �            1259    17384    users    TABLE     _  CREATE TABLE public.users (
    id integer NOT NULL,
    first_name character varying(50) NOT NULL,
    last_name character varying(50) NOT NULL,
    email character varying(100) NOT NULL,
    password character varying(255) NOT NULL,
    is_doctor boolean NOT NULL,
    medicallicensenumber character varying,
    specialization character varying
);
    DROP TABLE public.users;
       public         heap    postgres    false            �            1259    17383    users_id_seq    SEQUENCE     �   CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.users_id_seq;
       public          postgres    false    215            <           0    0    users_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;
          public          postgres    false    214            �           2604    17411    health_data id    DEFAULT     p   ALTER TABLE ONLY public.health_data ALTER COLUMN id SET DEFAULT nextval('public.health_data_id_seq'::regclass);
 =   ALTER TABLE public.health_data ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    218    217    218            �           2604    17423    medicine_reminders id    DEFAULT     ~   ALTER TABLE ONLY public.medicine_reminders ALTER COLUMN id SET DEFAULT nextval('public.medicine_reminders_id_seq'::regclass);
 D   ALTER TABLE public.medicine_reminders ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    220    219    220            �           2604    17435    recommendations id    DEFAULT     x   ALTER TABLE ONLY public.recommendations ALTER COLUMN id SET DEFAULT nextval('public.recommendations_id_seq'::regclass);
 A   ALTER TABLE public.recommendations ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    222    221    222            �           2604    17387    users id    DEFAULT     d   ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);
 7   ALTER TABLE public.users ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    215    214    215            2          0    17447    appointments 
   TABLE DATA           C   COPY public.appointments (doctor_id, patient_id, date) FROM stdin;
    public          postgres    false    223   L3       +          0    17392    doctor_patient 
   TABLE DATA           ?   COPY public.doctor_patient (doctor_id, patient_id) FROM stdin;
    public          postgres    false    216   �3       -          0    17408    health_data 
   TABLE DATA           [   COPY public.health_data (id, user_id, weight, height, steps, heart_rate, date) FROM stdin;
    public          postgres    false    218   �3       /          0    17420    medicine_reminders 
   TABLE DATA           p   COPY public.medicine_reminders (id, user_id, medicine_name, dosage, schedule, start_date, end_date) FROM stdin;
    public          postgres    false    220   "4       1          0    17432    recommendations 
   TABLE DATA           Q   COPY public.recommendations (id, user_id, recommendation_text, date) FROM stdin;
    public          postgres    false    222   �4       *          0    17384    users 
   TABLE DATA           |   COPY public.users (id, first_name, last_name, email, password, is_doctor, medicallicensenumber, specialization) FROM stdin;
    public          postgres    false    215   �5       =           0    0    health_data_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.health_data_id_seq', 6, true);
          public          postgres    false    217            >           0    0    medicine_reminders_id_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public.medicine_reminders_id_seq', 5, true);
          public          postgres    false    219            ?           0    0    recommendations_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.recommendations_id_seq', 4, true);
          public          postgres    false    221            @           0    0    users_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.users_id_seq', 7, true);
          public          postgres    false    214            �           2606    17396 "   doctor_patient doctor_patient_pkey 
   CONSTRAINT     s   ALTER TABLE ONLY public.doctor_patient
    ADD CONSTRAINT doctor_patient_pkey PRIMARY KEY (doctor_id, patient_id);
 L   ALTER TABLE ONLY public.doctor_patient DROP CONSTRAINT doctor_patient_pkey;
       public            postgres    false    216    216            �           2606    17413    health_data health_data_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.health_data
    ADD CONSTRAINT health_data_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.health_data DROP CONSTRAINT health_data_pkey;
       public            postgres    false    218            �           2606    17425 *   medicine_reminders medicine_reminders_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public.medicine_reminders
    ADD CONSTRAINT medicine_reminders_pkey PRIMARY KEY (id);
 T   ALTER TABLE ONLY public.medicine_reminders DROP CONSTRAINT medicine_reminders_pkey;
       public            postgres    false    220            �           2606    17439 $   recommendations recommendations_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.recommendations
    ADD CONSTRAINT recommendations_pkey PRIMARY KEY (id);
 N   ALTER TABLE ONLY public.recommendations DROP CONSTRAINT recommendations_pkey;
       public            postgres    false    222            �           2606    17391    users users_email_key 
   CONSTRAINT     Q   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_email_key UNIQUE (email);
 ?   ALTER TABLE ONLY public.users DROP CONSTRAINT users_email_key;
       public            postgres    false    215            �           2606    17389    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    215            �           2606    17397 ,   doctor_patient doctor_patient_doctor_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.doctor_patient
    ADD CONSTRAINT doctor_patient_doctor_id_fkey FOREIGN KEY (doctor_id) REFERENCES public.users(id);
 V   ALTER TABLE ONLY public.doctor_patient DROP CONSTRAINT doctor_patient_doctor_id_fkey;
       public          postgres    false    3469    216    215            �           2606    17402 -   doctor_patient doctor_patient_patient_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.doctor_patient
    ADD CONSTRAINT doctor_patient_patient_id_fkey FOREIGN KEY (patient_id) REFERENCES public.users(id);
 W   ALTER TABLE ONLY public.doctor_patient DROP CONSTRAINT doctor_patient_patient_id_fkey;
       public          postgres    false    215    3469    216            �           2606    17414 $   health_data health_data_user_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.health_data
    ADD CONSTRAINT health_data_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);
 N   ALTER TABLE ONLY public.health_data DROP CONSTRAINT health_data_user_id_fkey;
       public          postgres    false    3469    215    218            �           2606    17426 2   medicine_reminders medicine_reminders_user_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.medicine_reminders
    ADD CONSTRAINT medicine_reminders_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);
 \   ALTER TABLE ONLY public.medicine_reminders DROP CONSTRAINT medicine_reminders_user_id_fkey;
       public          postgres    false    3469    215    220            �           2606    17440 ,   recommendations recommendations_user_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.recommendations
    ADD CONSTRAINT recommendations_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);
 V   ALTER TABLE ONLY public.recommendations DROP CONSTRAINT recommendations_user_id_fkey;
       public          postgres    false    215    3469    222            2   5   x�-��  �7ݥ��ø�Ƅ�]�4�������M�ovuVݢ&� � Z�
i      +      x�3�4�2�4�2�4b3�=... T�      -   V   x�U�A
� D���.�I�F������B��Q(�,$zcȗ��b��bh�mvݜ?����!�4-�k|�j��v����!@      /   �   x����� @��+��
�������B�Y/��Pl�_/��vt��%�� `}��M$�z�`Z��������[<����J����JRgl2	l0R�;9�U��\�6�$ɸL�ꔳ���`��^���/I�P��hأ�Y�~$��bU�w��E>�i��91�˱d�= >�`	      1   �   x�u�1R�0Ek�?=��vg��KI��ב�bi�u�n�E`���̾�����&FZ"C<�D�ޠ�їM09ٞ�{��w8��Z�`��G�Ĉ6M�#����|�ݴ{}�7Z5��:"�%�#��H¸xN�����O����Ȏ>�AL�s@W�b�|�ŬU[���Ǖ�,e@ىɠ�_f�R���8h���@W�����u	�#ʻ��Q���a�ί8�� 1���Dl��U�z?(�� ��e      *   �  x�m�9��@���Vm��-x"�"#�5I-w7r
�~ٙZ���7���9X�U��/�H����
��t@
0��l��L9PF�>g���=��O��x}SC����>J��Ӻ)�PO�̝IB� w�iM�Z�&�z��������.�u-a�'�q�����Z�Q�$^�^J7^bV�'�>ڠ'Jla���OLBt�礇H���{�³?�]?,��D�W���.q���X��+ˮR{͔��A�O 綊�� ,������z�Yv�T]<���,��q��kT"�HS�����}��FH�x;����{m(N�`��(c[�0�w�C�-t؇p���nx?�S��=.be*{:
�.5̼�R����y0�� i��3&�4�g���޵�|W�8�r�)�06Nx����v�l���)\{x��IS�Q��r     