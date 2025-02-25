CREATE TABLE IF NOT EXISTS public.book (
    id UUID PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    isbn VARCHAR(255) UNIQUE NOT NULL,
    author_name VARCHAR(255) NOT NULL,
    category VARCHAR(255) NOT NULL,
    availability VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS public.borrower (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone_number VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS public.author (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    biography TEXT
);

CREATE TABLE IF NOT EXISTS public.transaction (
    id UUID PRIMARY KEY,
    borrow_date DATE,
    return_date DATE,
    status VARCHAR(255) NOT NULL,
    book_id UUID NOT NULL,
    borrower_id UUID NOT NULL,
    FOREIGN KEY (book_id) REFERENCES book(id) ON DELETE CASCADE,
    FOREIGN KEY (borrower_id) REFERENCES borrower(id) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS book_author (
    id UUID PRIMARY KEY,
    book_id UUID NOT NULL,
    author_id UUID NOT NULL,
    FOREIGN KEY (book_id) REFERENCES book(id) ON DELETE CASCADE,
    FOREIGN KEY (author_id) REFERENCES author(id) ON DELETE CASCADE
);
