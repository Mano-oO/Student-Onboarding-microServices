FROM python:3.10-slim

WORKDIR /M1project

COPY req.txt .

RUN pip install --no-cache-dir -r req.txt

COPY . .

CMD ["python", "app.py"]