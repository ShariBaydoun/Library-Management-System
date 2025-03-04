
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns
                   WHERE table_name = 'book' AND column_name = 'price') THEN
        ALTER TABLE public.book ADD COLUMN price DECIMAL(10, 2) NOT NULL DEFAULT 0;
    END IF;
END $$;
